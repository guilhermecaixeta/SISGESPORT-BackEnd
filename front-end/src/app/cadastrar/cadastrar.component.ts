import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../router.animations';
import { Service } from '../service/service.component';
import { BaseComponent } from '../base-components/base.component';

@Component({
    selector: 'app-cadastrar',
    templateUrl: './cadastrar.component.html',
    styleUrls: ['./cadastrar.component.scss'],
    animations: [routerTransition()]
})
export class CadastrarComponent implements OnInit {
 ngOnInit() {}
}
