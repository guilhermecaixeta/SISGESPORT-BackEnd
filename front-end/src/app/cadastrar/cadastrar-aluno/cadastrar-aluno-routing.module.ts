import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CadastrarAlunoComponent } from './cadastrar-aluno.component';

const routes: Routes = [
    {
        path: '', component: CadastrarAlunoComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class CadastrarAlunoRoutingModule {
}
