/*
 * MapServiceConfiguration.ktn.kt
 *
 * Copyright * All Rights Reserved.
 *
 * **.
 * Use is subject to license terms.
 */
package com.advanon.test.assignment.service.map

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("app.map-api")
class MapServiceConfiguration(var url: String?)