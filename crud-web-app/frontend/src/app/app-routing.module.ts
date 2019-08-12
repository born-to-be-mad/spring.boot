import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {CardListComponent} from "./card-list/card-list.component";
import {UserListComponent} from './user-list/user-list.component';
import {UserFormComponent} from './user-form/user-form.component';
import {PlayCardComponent} from "./play-card/play-card.component";
import {AdminPanelComponent} from "./admin-panel/admin-panel.component";

const routes: Routes = [
  {
    path: 'admin', component: AdminPanelComponent,
    children: [
      {path: 'cards', component: CardListComponent},
      {path: 'users', component: UserListComponent},
      {path: 'adduser', component: UserFormComponent}
    ]
  },
  {path: 'play', component: PlayCardComponent}
];

@NgModule({
            imports: [RouterModule.forRoot(routes)],
            exports: [RouterModule]
          })
export class AppRoutingModule {
}
