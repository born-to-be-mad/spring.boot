import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../model/user';
import {Observable} from 'rxjs';

@Injectable()
export class UserService {

  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8100/api/users';
  }

  public findAll(): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl);
  }

  public create(user: User) {
    return this.http.post<User>(this.usersUrl, user);
  }

  public delete(id: number): Observable<any> {
    return this.http.delete(`${this.usersUrl}/${id}`, {responseType: 'text'});
  }

  get(id: number): Observable<Object> {
    return this.http.get(`${this.usersUrl}/${id}`);
  }

  update(id: number, userData: any): Observable<Object> {
    return this.http.post(`${this.usersUrl}/${id}`, userData);
  }
}
