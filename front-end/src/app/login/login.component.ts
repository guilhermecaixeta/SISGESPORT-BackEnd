import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { routerTransition } from '../router.animations';
import { BaseCrudComponent } from '../base';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
    animations: [routerTransition()]
})
export class LoginComponent extends BaseCrudComponent {
    
    usuario = {
    senha: '',
    matricula: ''
    };

    onLoggedin() {
        this.service.Login(this.usuario);
    }
}
