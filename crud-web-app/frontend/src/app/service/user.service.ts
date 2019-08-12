import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../model/user';
import {Observable} from 'rxjs';

@Injectable()
export class UserService {

  private baseUrl : string;

  constructor(private http: HttpClient) {
    this.baseUrl = '/api/users';
  }

  public findAll(): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}`);
  }

  public create(user: User) {
    return this.http.post<User>(`${this.baseUrl}`, user);
  }

  public delete(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, {responseType: 'text'});
  }

  get(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  update(id: number, userData: any): Observable<Object> {
    return this.http.post(`${this.baseUrl}/${id}`, userData);
  }
}
