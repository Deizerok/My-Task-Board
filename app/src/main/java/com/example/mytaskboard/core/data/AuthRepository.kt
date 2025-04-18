package com.example.mytaskboard.core.data

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.example.mytaskboard.R
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface AuthRepository {

    fun isUserLogged(): Boolean

    suspend fun loginWithGoogle(context: Context): AuthResult

    fun userEmail(): String

    fun signOut()

    class Base @Inject constructor(
        private var auth: FirebaseAuth,
        private val provideResources: ProvideResources,
    ) : AuthRepository {

        override fun isUserLogged() = auth.currentUser != null

        override suspend fun loginWithGoogle(context: Context): AuthResult {

            val credentialManager = CredentialManager.create(context)

            val googleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setAutoSelectEnabled(false)
                .setServerClientId(context.getString(R.string.default_web_client_id))
                .build()

            val request: GetCredentialRequest = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()
            return try {
                val credential = credentialManager
                    .getCredential(context = context, request = request)
                    .credential

                if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {

                    try {
                        val googleIdToken = GoogleIdTokenCredential
                            .createFrom(credential.data).idToken
                        val authCredential = GoogleAuthProvider
                            .getCredential(googleIdToken, null)

                        Firebase.auth.signInWithCredential(authCredential).await()

                        AuthResult.Success
                    } catch (e: Exception) {
                        AuthResult.Error(message = "Failed to parse an TYPE_GOOGLE_ID_TOKEN_CREDENTIAL: ${e.message}")
                    }
                } else {
                    AuthResult.Error(message = "Unexpected type of credential")
                }
            } catch (e: Exception) {
                AuthResult.Error(message = "Unexpected type of credential")
            }
        }

        override fun userEmail() = auth.currentUser!!.email!!

        override fun signOut() = auth.signOut()
    }
}