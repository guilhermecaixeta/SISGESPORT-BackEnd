import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { BaseComponent } from './base.component';

@Component({
    selector: 'app-base-crud',
    template: ''
})
export class BaseCrudComponent extends BaseComponent {
    id: any;
    /**
     * Formulário padrão para a persistencia de dados da aplicação.
     */
    formulario: FormGroup;
    /**
     * Objeto de retorno do back end.
     */
    objetoRetorno: any;
    /**
     * Variavel usada para identificar a etapa na qual o usuário se encontra em um crud que é necessário mais de uma etapa.
     */
    etapa = 0;
    /**
     * Número total de etapas do crud a ser preenchido pelo usuário. Esse valor deve ser alterado conforme a necessidade do crud.
     */
    etapasTotal = 2;

    validacaoData: RegExp = /(((\d{4})-(\d{2})-(\d{2})T(\d{2})\:(\d{2})\:(\d{2}))([+-](\d{2})\:(\d{2}))?Z?z?)/;
    /**
     * Objeto usado para realizar a multipla validação de dados em uma etapa. Deve ser implementado na classe filha.
     */
    multiValidacao =
        {
            formulario: this.formulario,
            eValido: false,
            validarEtapa: (opt?: any) => this.validarEtapa(opt)
        };

    ngOnInit() {

        this.iniciar();

        this.activatedRoute.params.subscribe(param => {
            this.id = param['id'];
            let linkLista = location.href.split('/');
            this.acao = (param['acao'] !== undefined ? param['acao'] : "cadastrar").toLowerCase();
        });

        switch (this.acao) {
            case 'visualizar':
                this.etapa = (this.etapasTotal - 1);
                this.formulario.disable();
            case 'editar':
                this.carregarDados();
                break;
        }
        this.aposIniciar();
    }

    /**
     * Carrega os dados usados para editar um dado.
     */
    carregarDados() {
        this.service.Get(this.rota, this.id).subscribe(obj => {
            Object.keys(obj).forEach(key => {
                obj[key] = this.validacaoData.test(obj[key]) ? new Date(obj[key]) : obj[key];
            });
            this.objetoRetorno = obj;
            this.formulario.patchValue(this.objetoRetorno);
        });
    }
    /**
     * Metodo de persistencia do sistema. A variaval ação diz qual deve ser a ação realizada pelo método.
     * @param obj Objeto a ser persisitido
     */
    Persistir(obj: any) {
        this.acao == 'cadastrar' ?
            this.service.Post(this.rota, obj).subscribe(
                () => this.router.navigate(['../'], { relativeTo: this.activatedRoute }),
                err => console.log('Ocorreu algum erro no servidor: ', err)
            ) : this.service.Put(this.rota, obj);
    }
}
