/*
 * TileRepository.kt
 *
 * Copyright * All Rights Reserved.
 *
 * **.
 * Use is subject to license terms.
 */
package com.advanon.test.assignment.data.repository

import com.advanon.test.assignment.data.entity.TileEntity
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface TileRepository : PagingAndSortingRepository<TileEntity, Long> {

}
