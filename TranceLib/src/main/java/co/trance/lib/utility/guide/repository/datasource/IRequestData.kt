package co.trance.lib.utility.guide.repository.datasource

import org.json.JSONObject

interface IRequestData {
    public fun parseJson(): JSONObject
}