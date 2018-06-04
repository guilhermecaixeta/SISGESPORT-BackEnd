import {HttpHeaders }from '@angular/common/http'; 

const httpOptions ={
    headers: new HttpHeaders( {
    'Content-Type':'application/json', 
    'Access-Control-Allow-Origin':'*', 
    'Access-Control-Allow-Credentials':'true', 
    'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE', 
    'Access-Control-Allow-Headers':'Content-Type'
    })
}; 

/**
 * Retorna o header a ser usado na requisição e caso o usuário possua token ele é adicionado na requisição. 
 */
export function getHeader() {
    if (localStorage.getItem('token') != null) {
        httpOptions.headers.set('Authorization', `Bearer ${localStorage.getItem('token')}`);
    }
    return httpOptions;
}
