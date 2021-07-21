/*
 * MapController.kt
 *
 * Copyright * All Rights Reserved.
 *
 * **.
 * Use is subject to license terms.
 */
package com.advanon.test.assignment.web

import com.advanon.test.assignment.service.map.MapService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.nio.charset.Charset
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/api/map")
class MapController(val mapService: MapService) {

    @PostMapping(path = [""], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun initMaps() {
        val mapDto = mapService.getMaps()
        mapService.save(mapDto)
    }

    @RequestMapping(
            method = [RequestMethod.GET],
            path = ["/asciiMap"])
    @ResponseBody
    fun getFirstAsciiMap(response: HttpServletResponse) {
        getAsciiMapById(null, response)
    }

    @RequestMapping(
            method = [RequestMethod.GET],
            path = ["/asciiMap/{id}"])
    @ResponseBody
    fun getAsciiMapById(@PathVariable id: String?, response: HttpServletResponse) {
        val map = mapService.getAsciiMap(id)
        response.contentType = MediaType.TEXT_PLAIN_VALUE
        response.outputStream.write(map.toByteArray(Charset.forName("UTF-8")))
    }

}
