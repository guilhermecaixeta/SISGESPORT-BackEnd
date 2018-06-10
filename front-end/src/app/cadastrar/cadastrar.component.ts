import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../router.animations';

@Component({
    selector: 'app-cadastrar',
    templateUrl: './cadastrar.component.html',
    styleUrls: ['./cadastrar.component.scss'],
    animations: [routerTransition()]
})
export class CadastrarComponent implements OnInit {
 ngOnInit() {}
}
