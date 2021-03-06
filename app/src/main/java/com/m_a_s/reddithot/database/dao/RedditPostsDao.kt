package com.m_a_s.reddithot.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.m_a_s.reddithot.models.RedditPost

@Dao
interface RedditPostsDao {

    @Insert(onConflict = REPLACE)
    suspend fun savePosts(redditPosts: List<RedditPost>)

    @Query("SELECT * FROM redditPosts")
    fun getPosts(): PagingSource<Int, RedditPost>

}