package co.trance.lib.utility.guide.repository.datasource

import org.json.JSONObject

interface IResponseBody {
    fun parse(json: JSONObject): IResponseBody
}