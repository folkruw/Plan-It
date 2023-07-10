package com.example.planit.announcements

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planit.dtos.DtoInputAnnouncements
import com.example.planit.dtos.DtoOutputFetcbByFunction
import com.example.planit.repositories.AnnouncementsRepository
import com.example.planit.utils.RetrofitHelper
import com.example.planit.utils.Session
import kotlinx.coroutines.launch

class AnnouncementsManagerViewModel : ViewModel() {
    private val announcementsRepository = RetrofitHelper.create()
        .create(AnnouncementsRepository::class.java)

    private val session : Session = Session()
    val mutableListAnnouncements : MutableLiveData<List<DtoInputAnnouncements>> = MutableLiveData()

    fun launchFetchAllAnnouncements() {
        viewModelScope.launch {
            var idFunctions = 0
            idFunctions = if(session.getFunction() == "Directeur")
                5
            else
                2

            val dtoOutputFetcbByFunction = DtoOutputFetcbByFunction(0, 0)
            dtoOutputFetcbByFunction.idCompanies = session.getIDCompany()
            dtoOutputFetcbByFunction.idFunctions = idFunctions
            val announcementsList = announcementsRepository.fetchByFunction(dtoOutputFetcbByFunction)
            mutableListAnnouncements.postValue(announcementsList)
        }
    }
}
