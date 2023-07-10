package com.example.planit.events.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planit.dtos.DtoInputEvent
import com.example.planit.dtos.DtoOutputCreateEvent
import com.example.planit.dtos.DtoOutputEvent
import com.example.planit.repositories.EventRepository
import com.example.planit.utils.RetrofitHelper
import com.example.planit.utils.Session
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.temporal.TemporalAdjusters.firstDayOfMonth
import java.time.temporal.TemporalAdjusters.lastDayOfMonth

class EventManagerViewModel : ViewModel() {
    private val eventRepository = RetrofitHelper.create()
        .create(EventRepository::class.java)

    private val session : Session = Session()
    val mutableListEvents : MutableLiveData<List<DtoInputEvent>> = MutableLiveData()
    val mutableNewRequestCreate: MutableLiveData<DtoInputEvent> = MutableLiveData()

    fun launchFetchAllEvents() {
        viewModelScope.launch {
            val current = LocalDateTime.now()

            val startDate = current.with(firstDayOfMonth()).withHour(0).withMinute(0).withSecond(0)
            val endDate = current.with(lastDayOfMonth()).withHour(23).withMinute(59).withSecond(0)

            val eventsList = eventRepository.fetchByEmployee(session.getIDCompany(), session.getID(), startDate, endDate)
            mutableListEvents.postValue(eventsList)
        }
    }

    fun launchFetchRequest() {
        viewModelScope.launch {
            var eventsList = eventRepository.fetchRequest(session.getID())
            eventsList = eventsList.filter { it.types.compareTo("Travail") != 0 && !it.isValid }
            mutableListEvents.postValue(eventsList)
        }
    }

    fun launchCreateRequest(dto: DtoOutputEvent) {
        viewModelScope.launch {
            val events = DtoOutputCreateEvent(dto)
            val dto = eventRepository.create(session.getIDCompany(), events)
            mutableNewRequestCreate.postValue(dto)
        }
    }
}
