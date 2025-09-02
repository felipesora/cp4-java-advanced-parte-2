let ferramentaIdParaExcluir = null;

function abrirModal(id) {
    ferramentaIdParaExcluir = id;
    document.getElementById('modalExcluir').style.display = 'flex';
}

function fecharModal() {
    ferramentaIdParaExcluir = null;
    document.getElementById('modalExcluir').style.display = 'none';
}

document.getElementById('confirmarExcluir').addEventListener('click', function() {
    if(ferramentaIdParaExcluir) {
      window.location.href = '/ferramentas/deletar/' + ferramentaIdParaExcluir;
    }
});