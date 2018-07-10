import { Component } from '@angular/core';
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
        this.service.Login(this.usuario).subscribe(
            () => this.router.navigate(['/dashboard'], { relativeTo: this.activatedRoute }),
            err => this.alertas.push({
                id: 1,
                type: 'danger',
                message: err
            })
        );
    }
}
