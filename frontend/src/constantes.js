/**
 * @file Contém as constantes do sistema
 */

var constantes = {
    LOCALE: 'pt-BR',
    formato: {
        HORA: "HH:mm",
    },
    BASE_URL: window.location.protocol+'//'+window.location.host,
    sistema: {
        sigla: 'csac',
        descricao: 'sadasds',
        /* Versão
         * 1º Dígito: Novos modúlos e funcionalidades não existentes
         * 2º Dígito: Melhorias, correções --- Milestone GitLab 
        */
        versao: 'v1.2', 
    },    
    dia_semana: [
        '', 'Domingo', 'Segunda-feira', 'Terça-feira', 'Quarta-feira', 'Quinta-feira', 'Sexta-feira', 'Sábado'
    ],
    mask: {
        CPF: '###.###.###-##',
        CPF_CNPJ: ['###.###.###-##', '##.###.###/####-##'],
        CNPJ: '##.###.###/####-##',
        CEP: '#####-##',
    },
    
    /* */
    moneyMask: {
        decimal: ',',
        thousands: '.',
        prefix: 'R$ ',
        suffix: '',
        precision: 2,
        masked: false /* doesn't work with directive */
    },    
    
    /* */
    percentMask: {
        decimal: ',',
        thousands: '.',
        prefix: '%',
        suffix: '',
        precision: 2,
        masked: false /* doesn't work with directive */
    },
    
    
    //essa constante determina qual mensagem do laravel corresponde a cada tipo de validação do vuelidate. Somente para as que não coincidem.
    msgTypeMap: {
        alphaNum: 'alpha_num',
        between: 'between.numeric',
        cpf: 'invalid',
        cnpj: 'invalid',
        cpf_cnpj: 'invalid',
        minLength: 'min.string',
        minValue: 'min.numeric',
        maxLength: 'max.string',
        maxValue: 'max.numeric',
        requiredIf: { key: 'required_with', params: { prop: 'values' }, ref: 'values' },
        requiredUnless: { key: 'required_without', params: { prop: 'values' }, ref: 'values' },
        sameAs: { key: 'same', params: { eq: 'other' }, ref: 'other' },
    },
    
    idGrupoPlanoConta_DESPESA: '3',
    idGrupoPlanoConta_RECEITA: '4',

}

constantes = Object.freeze(constantes);
export default constantes;