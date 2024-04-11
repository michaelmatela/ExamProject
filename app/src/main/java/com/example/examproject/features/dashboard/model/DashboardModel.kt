package com.example.examproject.features.dashboard.model

import com.google.gson.annotations.SerializedName

data class DashboardModel (

    @SerializedName("levels" ) var levels : ArrayList<Levels> = arrayListOf()

)

data class Levels (

    @SerializedName("level"       ) var level       : String?               = null,
    @SerializedName("title"       ) var title       : String?               = null,
    @SerializedName("description" ) var description : String?               = null,
    @SerializedName("state"       ) var state       : String?               = null,
    @SerializedName("activities"  ) var activities  : ArrayList<Activities> = arrayListOf()

)

data class Activities (

    @SerializedName("id"           ) var id           : String?     = null,
    @SerializedName("challengeId"  ) var challengeId  : String?     = null,
    @SerializedName("type"         ) var type         : String?     = null,
    @SerializedName("title"        ) var title        : String?     = null,
    @SerializedName("titleB"       ) var titleB       : String?     = null,
    @SerializedName("description"  ) var description  : String?     = null,
    @SerializedName("descriptionB" ) var descriptionB : String?     = null,
    @SerializedName("state"        ) var state        : String?     = null,
    @SerializedName("icon"         ) var icon         : Icon?       = Icon(),
    @SerializedName("lockedIcon"   ) var lockedIcon   : LockedIcon? = LockedIcon()

)

data class LockedIcon (

    @SerializedName("file"        ) var file        : File?   = File(),
    @SerializedName("title"       ) var title       : String? = null,
    @SerializedName("description" ) var description : String? = null

)

data class Icon (

    @SerializedName("file"        ) var file        : File?   = File(),
    @SerializedName("title"       ) var title       : String? = null,
    @SerializedName("description" ) var description : String? = null

)

data class File (

    @SerializedName("url"         ) var url         : String?  = null,
    @SerializedName("details"     ) var details     : Details? = Details(),
    @SerializedName("fileName"    ) var fileName    : String?  = null,
    @SerializedName("contentType" ) var contentType : String?  = null

)

data class Details (

    @SerializedName("size" ) var size : Int? = null

)

