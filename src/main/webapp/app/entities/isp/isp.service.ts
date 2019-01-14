import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IIsp } from 'app/shared/model/isp.model';

type EntityResponseType = HttpResponse<IIsp>;
type EntityArrayResponseType = HttpResponse<IIsp[]>;

@Injectable({ providedIn: 'root' })
export class IspService {
    public resourceUrl = SERVER_API_URL + 'api/isps';

    constructor(protected http: HttpClient) {}

    create(isp: IIsp): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(isp);
        return this.http
            .post<IIsp>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(isp: IIsp): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(isp);
        return this.http
            .put<IIsp>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IIsp>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IIsp[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(isp: IIsp): IIsp {
        const copy: IIsp = Object.assign({}, isp, {
            createTime: isp.createTime != null && isp.createTime.isValid() ? isp.createTime.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createTime = res.body.createTime != null ? moment(res.body.createTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((isp: IIsp) => {
                isp.createTime = isp.createTime != null ? moment(isp.createTime) : null;
            });
        }
        return res;
    }
}
