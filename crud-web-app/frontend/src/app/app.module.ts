import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { UserListComponent } from './user-list/user-list.component';
import { UserFormComponent } from './user-form/user-form.component';
import { UserService } from './service/user.service';
import { CardListComponent } from './card-list/card-list.component';
import { CardService } from "./service/card.service";
import { PlayCardComponent } from './play-card/play-card.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';

@NgModule({
            declarations: [
              AppComponent,
              UserListComponent,
              UserFormComponent,
              CardListComponent,
              PlayCardComponent,
              AdminPanelComponent
            ],
            imports: [
              BrowserModule,
              AppRoutingModule,
              HttpClientModule,
              FormsModule
            ],
            providers: [UserService, CardService],
            bootstrap: [AppComponent]
          })
export class AppModule { }
