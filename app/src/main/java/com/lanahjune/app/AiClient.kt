package com.lanahjune.app

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import org.json.JSONObject

object AiClient {
    suspend fun ask(prompt: String, apiKey: String): String =
        withContext(Dispatchers.IO) {
            val conn = URL("http://127.0.0.1:3001/v1/chat/completions")
                .openConnection() as HttpURLConnection
            try {
                conn.requestMethod = "POST"
                conn.connectTimeout = 15000
                conn.readTimeout = 90000
                conn.doOutput = true
                conn.setRequestProperty("Authorization", "Bearer $apiKey")
                conn.setRequestProperty("Content-Type", "application/json")

                val body = JSONObject()
                    .put("model", "auto")
                    .put("messages", org.json.JSONArray().put(
                        JSONObject().put("role", "user").put("content", prompt)
                    )).toString()

                conn.outputStream.use { it.write(body.toByteArray(Charsets.UTF_8)) }

                val code = conn.responseCode
                val stream = if (code in 200..299) conn.inputStream else conn.errorStream
                val response = stream.bufferedReader().use { it.readText() }

                if (code !in 200..299) error("HTTP $code: $response")

                JSONObject(response).getJSONArray("choices")
                    .getJSONObject(0).getJSONObject("message")
                    .getString("content")
            } finally {
                conn.disconnect()
            }
        }
}
