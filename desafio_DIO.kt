enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Usuario(val nome: String, val email: String, val idade: Int) {
    val historicoFormacoes = mutableListOf<Formacao>()
    val conteudosConcluidos = mutableSetOf<ConteudoEducacional>()
}

data class ConteudoEducacional(val nome: String, val duracao: Int = 60, val descricao: String, val recursos: List<String>)

class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        inscritos.add(usuario)
        usuario.historicoFormacoes.add(this)
    }

    fun listarMatriculados() {
        inscritos.forEach { println(it.nome) }
    }

    fun progressoFormacao(usuario: Usuario): Double {
        val conteudosConcluidos = usuario.conteudosConcluidos.intersect(conteudos).toList()
        val progresso = (conteudosConcluidos.size.toDouble() / conteudos.size) * 100
        return progresso
    }
}

fun main() {
    val conteudo1 = ConteudoEducacional("Introdução à Programação", descricao = "Aprenda os conceitos básicos de programação", recursos = listOf("link1", "link2"))
    val conteudo2 = ConteudoEducacional("Estruturas de Dados", descricao = "Estude sobre estruturas de dados em programação", recursos = listOf("link3", "link4"))

    val formacao1 = Formacao("Formação em Kotlin", listOf(conteudo1, conteudo2))

    val usuario1 = Usuario("João", "joao@email.com", 25)

    formacao1.matricular(usuario1)

    // Simulando conclusão de alguns conteúdos
    usuario1.conteudosConcluidos.add(conteudo1)
    
    formacao1.listarMatriculados()

    val progressoUsuario1 = formacao1.progressoFormacao(usuario1)
    println("Progresso de ${usuario1.nome} na formação: $progressoUsuario1%")
}
