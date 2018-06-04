import { Component, Input, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-validator',
  template: `
<div *ngIf="field.invalid && (field.dirty || field.touched)" class="alert alert-danger">
    <div *ngIf="field.errors.required">
        Campo obrigatório.
    </div>
    <div *ngIf="field.errors.minlength">
        O campo necessita ter ao menos {{field.errors.minlength['requiredLength']}} caracter(es).
    </div>
    <div *ngIf="field.errors.maxlength">
        O campo necessita ter no máximo {{field.errors.maxlength['requiredLength']}} caracter(es).
    </div>
    <div *ngIf="field.errors.min">
        O campo necessita ter o valor minímo de {{field.errors.min['min']}}.
    </div>
    <div *ngIf="field.errors.max">
        O campo necessita ter o valor máximo de {{field.errors.max['max']}}.
    </div>        
    <div *ngIf="field.errors.email">
        E-mail inválido.
    </div>
    <div *ngIf="field.errors.soNumero">
        Campo inválido.
    </div>  
    <div *ngIf="field.errors.ufValidation">
        UF inválido.
    </div>
    <div *ngIf="field.errors.minLengthCustom">
        {{field.errors.minLengthCustom}}
    </div>
    <div *ngIf="field.errors.maxLengthCustom">
       {{field.errors.maxLengthCustom}}
    </div>
    <div *ngIf="field.errors.dateMoreThen">
        {{field.errors.dateMoreThen}}.
    </div>
    <div *ngIf="field.errors.dateLessThen">
        {{field.errors.dateLessThen}}
    </div>                        
</div>
  `
})
export class BaseValidatorsComponent implements OnInit{
    @Input() field : FormControl;

    ngOnInit(){      }
}