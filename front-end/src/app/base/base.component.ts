import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormArray } from '@angular/forms';
import { Service } from '../service/service.component';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-base',
  template: ''
})
export class BaseComponent implements OnInit {

  constructor(
    public construtorFormulario: FormBuilder, 
    public service: Service,
    public router: Router,
    public activatedRoute: ActivatedRoute) { }

    /**
     * Rota a ser consumida pelo componente filho.
     */
    rota: string;
    /**
     * Ação a ser executada.
     */
    acao: any;
    /**
     * Método a ser executado ao iniciar o componente
     */
    init() { }
    /**
     * Método a ser executado após iniciar.
     */
    afterInit() { }
    /**
     * Metodo que carrega os componentes iniciais, não sobrescrever em componentes onde serão realizados os cruds.
     */
    ngOnInit() {    }

    /**
     * Verifica se uma variável é nula ou vazia.
     * @param value Variável a ser verificada
     */
    IsNullOrEmpty(value: string): any {
      if (value === null || value === undefined || value === '') {
        return true;
      } else {
          return false;
    }
  }

    /**
     * Verifica se uma string contêm determinado valor.
     * @param value String a ser verificada
     * @param comparer Valor a ser verificado se existe na string passada
     */
    Contains(value: string, comparer: string): any {
      if (value.search(comparer) < 0) {
        return false;
      } else {
        return true;
    }
  }

  /**
   * Seta todos os valores de um formulario percorrendo e marcado cada campo com o valor passado.
   * @param formGroup Formulário a ser tocado
   * @param func Tipo de função a ser usada no método, markAsDirty ou markAsPristine
   * @param opts Opcional
   */
  TocarTodos(formGroup: FormGroup | FormArray, func = 'markAsDirty', opts = { onlySelf: false }): void {
    Object.keys(formGroup.controls).map((key, index) => {
        let obj = formGroup.controls[key];
        if (obj instanceof FormGroup || obj instanceof FormArray)
            this.TocarTodos(obj, func, opts);
        else
            obj[func](opts);
    });
}
}
