package com.example.mytaskboard.taskboard.board.presentation.viewpageradapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mytaskboard.taskboard.done.DoneTaskFragment
import com.example.mytaskboard.taskboard.todo.presentation.ToDoTaskFragment

class ViewPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {


    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> ToDoTaskFragment()
        else -> {
            DoneTaskFragment()
        }
    }
}