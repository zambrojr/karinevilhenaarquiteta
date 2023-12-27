import moment from 'moment'
/**
 * 
 * @file Arquivo reservado para os helpers específicos do sistema.
 * Cada método que precisar ser disponibilizado publicamente deve estar devidamente declarado e exportado nesse arquivo.
*/


/**
 * Função apenas de teste
 */
const oi = (nome) => {
    alert(`Olá ${nome}`);
    console.log(`Olá ${nome}`);
}

const formatReal = (value) => {
    let val = (value/1).toFixed(2).replace('.', ',')
    return "R$ "+val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".")
}

const formatMonetario = (value) => {
    let val = (value/1).toFixed(2).replace('.', ',')
    return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".")
}

/**
 * Função de exemplo: Formata um número como cpf ou cnpj dependendo da quantidade de dígitos.
 * Não verifica se o número está correto, formata apenas se tiverem a quantidade exata de dígitos de cpf ou cnpf.
*/
const formataCpfCnpj = (num) => {
    if(num) {
        let n = num.replace(/\D/g, '');
        return (n.length == 11) //cpf
            ? `${n[0]}${n[1]}${n[2]}.${n[3]}${n[4]}${n[5]}.${n[6]}${n[7]}${n[8]}-${n[9]}${n[10]}`
            : (n.length == 14) //cnpj
                ? `${n[0]}${n[1]}.${n[2]}${n[3]}${n[4]}.${n[5]}${n[6]}${n[7]}/${n[8]}${n[9]}${n[10]}${n[11]}-${n[12]}${n[13]}`
                : num //tamanho inválido
    }
}


/**
 * Função de exemplo: Formata um número como cpf ou cnpj dependendo da quantidade de dígitos.
 * Não verifica se o número está correto, formata apenas se tiverem a quantidade exata de dígitos de cpf ou cnpf.
*/
const formataCEP = (num) => {
    if(num) {
        let n = num.replace(/\D/g, '');
        return  `${n[0]}${n[1]}${n[2]}${n[3]}${n[4]}-${n[5]}${n[6]}${n[7]}`
    }
}


/**
 * Função de exemplo: Formata um número como cpf ou cnpj dependendo da quantidade de dígitos.
 * Não verifica se o número está correto, formata apenas se tiverem a quantidade exata de dígitos de cpf ou cnpf.
*/
const formataTelefone = (num) => {
    if(num) {
        let n = num.replace(/\D/g, '');

        return (n.length == 11) ?
                `(${n[0]}${n[1]})${n[2]}${n[3]}${n[4]}${n[5]}${n[6]}-${n[7]}${n[8]}${n[9]}${n[10]}`
                : `(${n[0]}${n[1]})${n[2]}${n[3]}${n[4]}${n[5]}-${n[6]}${n[7]}${n[8]}${n[9]}`
    }
}


const dataHoraBrasil = (value) => {
    let val = moment(value, 'YYYY-MM-DD HH:mm:ss').format('DD/MM/YYYY HH:mm:ss');
    return val;
}

const dataElegant = (value) => {
    let val = moment(value, 'YYYY-MM-DD').format('DD MMM yyyy');
    return val;
}

const dataBrasil = (value) => {
    let val = moment(value, 'YYYY-MM-DD HH:mm:ss').format('DD/MM/YYYY');
    return val;
}

const dataUSA = (value) => {
    let val = moment(value, 'YYYY-MM-DD HH:mm:ss').format('YYYY-MM-DD');
    return val;
}

const dataBrforUS = (value) => {
    let val = moment(value, 'DD/MM/YYYY HH:mm:ss').format('YYYY-MM-DD');
    return val;
}


const dataVueCalendar = (value) => {
    let val = moment(value, moment.HTML5_FMT.DATETIME_LOCAL_MS).format('YYYY-MM-DD HH:mm:ss');
    return val;
}

//remova a função de teste e insira as funções que desejar
export default { oi, formatReal, formatMonetario, formataCpfCnpj, dataHoraBrasil, dataBrasil, dataUSA,dataBrforUS,formataCEP,formataTelefone, dataVueCalendar,dataElegant };
export { oi, formatReal, formatMonetario, formataCpfCnpj, dataHoraBrasil,formataCEP,formataTelefone, dataVueCalendar,dataElegant };
