function validarDados(){
    let nome = formContato.nome.value
    let fone = formContato.fone.value

    if(nome === ''){
        alert("PREENCHA O CAMPO NOME!")
        formContato.nome.focus()
    }
    else if(fone === ''){
        alert("PREENCHA O CAMPO TELEFONE!")
        formContato.fone.focus()
    }
    else{
        document.forms['formContato'].submit()
    }
}