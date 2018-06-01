import { Component, Input } from '@angular/core';

@Component({
    selector: 'app-menu-login-header',
    template: `
    <img src="assets/images/logo.png" width="150px" class="user-avatar" />
            <h1>{{Message}}</h1>
    `,
    styleUrls: ['../login/login.component.scss'],
})
export class MenuLoginHeaderComponent { 
    @Input() Message = 'Sistema de Gestão de Eventos Esportivos'; 
}