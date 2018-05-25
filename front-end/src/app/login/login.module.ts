import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoginRoutingModule } from './login-routing.module';
import { LoginComponent } from './login.component';
import { MenuLoginHeaderModule } from '../menu-login-header/menu-login.module';

@NgModule({
    imports: [CommonModule, LoginRoutingModule, MenuLoginHeaderModule ],
    declarations: [LoginComponent]
})
export class LoginModule {}
