import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CadastrarRoutingModule } from './cadastrar-routing.module';
import { CadastrarComponent } from './cadastrar.component';
import { MenuLoginHeaderModule } from '../menu-login-header/menu-login.module';
  
@NgModule({
  imports: [
    CommonModule,
    CadastrarRoutingModule,
    MenuLoginHeaderModule
  ],
  declarations: [CadastrarComponent]
})
export class CadastrarModule { }
