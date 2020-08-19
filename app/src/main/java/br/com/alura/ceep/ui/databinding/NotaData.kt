package br.com.alura.ceep.ui.databinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import br.com.alura.ceep.model.Nota

class NotaData(
    private var nota: Nota = Nota(),
    val titulo: MutableLiveData<String> = MutableLiveData<String>().also { it.value = nota.titulo },
    val descricao: MutableLiveData<String> = MutableLiveData<String>().also { it.value = nota.descricao },
    val favorita: MutableLiveData<Boolean> = MutableLiveData<Boolean>().also { it.value = nota.favorita },
    val imagemUrl: MutableLiveData<String> = MutableLiveData<String>().also { it.value = nota.imagemUrl }
) {
    val temUrl: LiveData<Boolean> = Transformations.map(imagemUrl) { url ->
        url.isNotEmpty()
    }

    // Esta função que irá receber a nota e atualizar as propriedades observáveis
    // permitindo o data binding
    fun atualiza(nota: Nota) {
        this.nota = nota
        titulo.postValue(this.nota.titulo)
        descricao.postValue(this.nota.descricao)
        favorita.postValue(this.nota.favorita)
        imagemUrl.postValue(this.nota.imagemUrl)
    }

    fun paraNota(): Nota? {
        return this.nota.copy(
            titulo = titulo.value ?: return null,
            descricao = descricao.value ?: return null,
            favorita = favorita.value ?: return null,
            imagemUrl = imagemUrl.value?: return null
        )
    }
}