package com.example.android.hilt.data

/**
 * @description
 * @mail chentaishan@aliyun.com
 * @date 2023/3/19
 */
interface LoggerDataSource {
    fun addLog(msg: String)
    fun getAllLogs(callback: (List<Log>) -> Unit)
    fun removeLogs()
}