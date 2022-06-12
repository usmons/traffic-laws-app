package io.usmon.trafficlaws.presentation.home_pager.util

import io.usmon.trafficlaws.domain.model.Law

// Created by Usmon Abdurakhmanv on 6/11/2022.

sealed class HomePagerEvent {
    data class LawClicked(val law: Law) : HomePagerEvent()
    data class LikeLawClicked(val law: Law) : HomePagerEvent()
    data class EditLawClicked(val law: Law) : HomePagerEvent()
    data class DeleteLawClicked(val law: Law) : HomePagerEvent()
    object UndoClicked : HomePagerEvent()
}
