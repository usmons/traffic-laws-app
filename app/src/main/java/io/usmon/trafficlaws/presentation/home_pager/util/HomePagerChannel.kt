package io.usmon.trafficlaws.presentation.home_pager.util

import io.usmon.trafficlaws.core.UiText
import io.usmon.trafficlaws.domain.model.Law

// Created by Usmon Abdurakhmanv on 6/11/2022.

sealed class HomePagerChannel {
    data class Details(val law: Law) : HomePagerChannel()
    data class Edit(val law: Law) : HomePagerChannel()
    data class ShowSnackbar(val message: UiText) : HomePagerChannel()
}
