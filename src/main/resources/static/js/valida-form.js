const form = document.getElementById('formFerramenta');
const erroParagrafo = document.getElementById('erroFormulario');

form.addEventListener('submit', function(event) {
    let erros = [];

    const nome = document.getElementById('nome').value.trim();
    const tipo = document.getElementById('tipo').value.trim();
    const classificacao = document.getElementById('classificacao').value.trim();
    const tamanho = document.getElementById('tamanho').value.trim();
    const preco = document.getElementById('preco').value;
    const quantidade = document.getElementById('quantidade').value;

    if(!nome) erros.push("Nome é obrigatório.");
    if(!tipo) erros.push("Tipo é obrigatório.");
    if(!classificacao) erros.push("Classificação é obrigatório.");
    if(!tamanho) erros.push("Tamanho é obrigatório.");
    if(!preco || Number(preco) <= 0) erros.push("Preço deve ser maior que zero.");
    if(!quantidade || Number(quantidade) <= 0) erros.push("Quantidade deve ser maior que zero.");

    if(erros.length > 0) {
        event.preventDefault();
        erroParagrafo.innerHTML = erros.join("<br>");
        erroParagrafo.style.display = "block";
    } else {
        erroParagrafo.style.display = "none";
    }
});
