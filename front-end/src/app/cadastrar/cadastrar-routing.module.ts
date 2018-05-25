import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CadastrarComponent } from './cadastrar.component';

const routes: Routes = [
    {
        path: '',
        component: CadastrarComponent
        // children: [
        //     { path: 'cadastrar-aluno', loadChildren: './cadastrar-aluno/cadastrar-aluno.module#CadastrarAlunoModule' },
        // ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class CadastrarRoutingModule {
}
