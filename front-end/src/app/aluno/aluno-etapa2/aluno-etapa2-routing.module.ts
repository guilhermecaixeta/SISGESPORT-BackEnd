import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AlunoEtapa2Component } from './aluno-etapa2.component';

const routes: Routes = [
    {
        path: '', component: AlunoEtapa2Component
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AlunoEtapa2RoutingModule {
}
