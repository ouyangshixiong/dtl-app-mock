/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { BankcardAuthRecordService } from 'app/entities/bankcard-auth-record/bankcard-auth-record.service';
import { IBankcardAuthRecord, BankcardAuthRecord } from 'app/shared/model/bankcard-auth-record.model';

describe('Service Tests', () => {
    describe('BankcardAuthRecord Service', () => {
        let injector: TestBed;
        let service: BankcardAuthRecordService;
        let httpMock: HttpTestingController;
        let elemDefault: IBankcardAuthRecord;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(BankcardAuthRecordService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new BankcardAuthRecord(
                0,
                'AAAAAAA',
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                0,
                0,
                'AAAAAAA',
                'AAAAAAA',
                currentDate,
                currentDate
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        createTime: currentDate.format(DATE_TIME_FORMAT),
                        lastModifyTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a BankcardAuthRecord', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        createTime: currentDate.format(DATE_TIME_FORMAT),
                        lastModifyTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        createTime: currentDate,
                        lastModifyTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new BankcardAuthRecord(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a BankcardAuthRecord', async () => {
                const returnedFromService = Object.assign(
                    {
                        txnId: 'BBBBBB',
                        userId: 1,
                        bankName: 'BBBBBB',
                        bankAccountName: 'BBBBBB',
                        bankCardNum: 'BBBBBB',
                        bankCardImgId: 1,
                        authStatus: 1,
                        auditOpinion: 'BBBBBB',
                        auditStaffName: 'BBBBBB',
                        createTime: currentDate.format(DATE_TIME_FORMAT),
                        lastModifyTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        createTime: currentDate,
                        lastModifyTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of BankcardAuthRecord', async () => {
                const returnedFromService = Object.assign(
                    {
                        txnId: 'BBBBBB',
                        userId: 1,
                        bankName: 'BBBBBB',
                        bankAccountName: 'BBBBBB',
                        bankCardNum: 'BBBBBB',
                        bankCardImgId: 1,
                        authStatus: 1,
                        auditOpinion: 'BBBBBB',
                        auditStaffName: 'BBBBBB',
                        createTime: currentDate.format(DATE_TIME_FORMAT),
                        lastModifyTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        createTime: currentDate,
                        lastModifyTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a BankcardAuthRecord', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
