package com.manuelnunez.apps.core.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.manuelnunez.apps.core.data.mapper.toItems
import com.manuelnunez.apps.core.domain.model.Item
import com.manuelnunez.apps.core.services.exception.NetworkException
import com.manuelnunez.apps.core.services.exception.ServiceException
import com.manuelnunez.apps.core.services.service.PexelsService
import retrofit2.HttpException
import java.io.IOException

class PexeelsCatsPagingSource(private val apiService: PexelsService) : PagingSource<Int, Item>() {
  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
    return try {
      val page = params.key ?: 1 // Default to first page if key is null

      val response = apiService.searchCatsPaginated(page = page)

      val items = response.toItems()
      LoadResult.Page(
          data = items,
          prevKey = if (page == 1) null else page - 1,
          nextKey = if (items.isEmpty()) null else page + 1)
    } catch (exception: NetworkException) {
      LoadResult.Error(exception)
    } catch (exception: ServiceException) {
      LoadResult.Error(exception)
    } catch (exception: IOException) {
      LoadResult.Error(exception)
    } catch (exception: HttpException) {
      LoadResult.Error(exception)
    } catch (exception: Exception) {
      LoadResult.Error(Exception())
    }
  }

  // Optional: Implement invalidate to handle refreshing data
  override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
    return state.anchorPosition?.let { anchorPosition ->
      state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
          ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
    }
  }
}
