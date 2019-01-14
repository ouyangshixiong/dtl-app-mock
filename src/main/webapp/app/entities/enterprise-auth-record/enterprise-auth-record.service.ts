import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEnterpriseAuthRecord } from 'app/shared/model/enterprise-auth-record.model';

type EntityResponseType = HttpResponse<IEnterpriseAuthRecord>;
type EntityArrayResponseType = HttpResponse<IEnterpriseAuthRecord[]>;

@Injectable({ providedIn: 'root' })
export class EnterpriseAuthRecordService {
    public resourceUrl = SERVER_API_URL + 'api/enterprise-auth-records';

    constructor(protected http: HttpClient) {}

    create(enterpriseAuthRecord: IEnterpriseAuthRecord): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(enterpriseAuthRecord);
        return this.http
            .post<IEnterpriseAuthRecord>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(enterpriseAuthRecord: IEnterpriseAuthRecord): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(enterpriseAuthRecord);
        return this.http
            .put<IEnterpriseAuthRecord>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEnterpriseAuthRecord>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEnterpriseAuthRecord[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(enterpriseAuthRecord: IEnterpriseAuthRecord): IEnterpriseAuthRecord {
        const copy: IEnterpriseAuthRecord = Object.assign({}, enterpriseAuthRecord, {
            createTime:
                enterpriseAuthRecord.createTime != null && enterpriseAuthRecord.createTime.isValid()
                    ? enterpriseAuthRecord.createTime.toJSON()
                    : null,
            lastModifyTime:
                enterpriseAuthRecord.lastModifyTime != null && enterpriseAuthRecord.lastModifyTime.isValid()
                    ? enterpriseAuthRecord.lastModifyTime.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createTime = res.body.createTime != null ? moment(res.body.createTime) : null;
            res.body.lastModifyTime = res.body.lastModifyTime != null ? moment(res.body.lastModifyTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((enterpriseAuthRecord: IEnterpriseAuthRecord) => {
                enterpriseAuthRecord.createTime = enterpriseAuthRecord.createTime != null ? moment(enterpriseAuthRecord.createTime) : null;
                enterpriseAuthRecord.lastModifyTime =
                    enterpriseAuthRecord.lastModifyTime != null ? moment(enterpriseAuthRecord.lastModifyTime) : null;
            });
        }
        return res;
    }
}
