import {Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup } from '@angular/forms';
import {Service } from '../service/service.component';
import {BaseComponent } from './base.component';

@Component( {
    selector: 'app-base-crud',
    template: ''
})
export class BaseCrudComponent extends BaseComponent {
    /**
     * Formulário padrão para a persistencia de dados da aplicação.
     */
    formulario: FormGroup;

    /**
     * Metodo de persistencia do sistema. A variaval ação diz qual deve ser a ação realizada pelo método.
     * @param obj Objeto a ser persisitido
     */
    Persistir(obj: any) {
    this.acao == 'cadastrar' ?
    this.service.Post(this.rota, obj) : this.service.Put(this.rota, obj);
    }
}
