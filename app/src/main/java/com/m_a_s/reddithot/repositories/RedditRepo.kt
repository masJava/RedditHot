package com.m_a_s.reddithot.repositories

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.m_a_s.reddithot.database.RedditDatabase
import com.m_a_s.reddithot.models.RedditPost
import com.m_a_s.reddithot.networking.RedditClient
import com.m_a_s.reddithot.networking.RedditService
import kotlinx.coroutines.flow.Flow

class RedditRepo(context: Context) {
    private val redditService = RedditClient.getClient().create(RedditService::class.java)
    private val redditDatabase = RedditDatabase.create(context)

    @OptIn(ExperimentalPagingApi::class)
    fun fetchPosts(): Flow<PagingData<RedditPost>> {
        return Pager(
            PagingConfig(pageSize = 40, enablePlaceholders = false, prefetchDistance = 3),
            remoteMediator = RedditRemoteMediator(redditService, redditDatabase),
            pagingSourceFactory = { redditDatabase.redditPostsDao().getPosts() }
        ).flow
    }
}