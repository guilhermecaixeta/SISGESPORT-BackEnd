import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BaseComponent, BaseCrudComponent, BaseValidatorsComponent, 
  BaseCrudNavegacaoComponent, BaseEtapaComponent } from '../base/index';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: 
  [
    BaseComponent, 
    BaseCrudComponent, 
    BaseValidatorsComponent, 
    BaseCrudNavegacaoComponent, 
    BaseEtapaComponent
  ],
  exports: 
  [
    BaseValidatorsComponent, 
    BaseCrudNavegacaoComponent, 
    BaseEtapaComponent
  ]
})
export class BaseModule { }
