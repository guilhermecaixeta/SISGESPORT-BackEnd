import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AlunoEtapa1Component } from './aluno-etapa1.component';

const routes: Routes = [
    {
        path: '', component: AlunoEtapa1Component
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AlunoEtapa1RoutingModule {
}
