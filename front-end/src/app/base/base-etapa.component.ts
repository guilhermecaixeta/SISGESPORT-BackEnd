import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { BaseComponent } from './base.component';

@Component({template:''})
export class BaseEtapaComponent extends BaseComponent {
    @Input() formulario : FormGroup;
    @Input() acao : string = "";
    @Input() rota : string = "";
    @Input() validacaoCustomizada = false;
    @Input() multiValidacao: any;
    @Output() multiValidacaoEmit: EventEmitter<any> = new EventEmitter<any>(true);
}