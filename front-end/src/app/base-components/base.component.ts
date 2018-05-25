import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Service } from '../service/service.component';

@Component({
  selector: 'app-base',
  template: ''
})
export class BaseComponent implements OnInit {

  constructor(public form: FormBuilder, public service: Service) { }

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

    ngOnInit() {
    this.init();
    }

    IsNullOrEmpty(value: string): any {
      if (value === null || value === undefined || value === '') {
        return true;
      } else {
          return false;
    }
  }

    Contains(value: string, comparer: string): any {
      if (value.search(comparer) < 0) {
        return false;
      } else {
        return true;
    }
}
}
