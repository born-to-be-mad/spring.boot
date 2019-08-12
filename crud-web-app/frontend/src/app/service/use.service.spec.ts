import {TestBed, inject} from '@angular/core/testing';

import {UserService} from './user.service';
import {HttpClient} from "@angular/common/http";

describe('UserService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
                                     providers: [
                                       {provide: HttpClient}
                                     ]
                                   });
  });

  it('should be created', inject([UserService], (service: UserService) => {
    expect(service).toBeTruthy();
  }));
});
