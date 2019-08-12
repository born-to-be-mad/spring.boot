import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {UserListComponent} from './user-list.component';
import {UserService} from "../service/user.service";
import {HttpClient} from "@angular/common/http";

describe('UserListComponent', () => {
  let component: UserListComponent;
  let fixture: ComponentFixture<UserListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
                                     declarations: [UserListComponent],
                                     providers: [
                                       {provide: UserService},
                                       {provide: HttpClient}
                                     ]
                                   })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
