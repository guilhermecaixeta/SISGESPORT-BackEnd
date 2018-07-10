import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoginRoutingModule } from './login-routing.module';
import { LoginComponent } from './login.component';
import { MenuLoginHeaderModule } from '../menu-login-header/menu-login.module';
import { FormsModule } from '@angular/forms';
import { NgbAlertModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
    imports: [CommonModule, 
        LoginRoutingModule, 
        MenuLoginHeaderModule, 
        FormsModule,
        NgbAlertModule.forRoot()
     ],
    declarations: [LoginComponent]
})
export class LoginModule {}
