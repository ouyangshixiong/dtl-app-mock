/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DtlAppMockTestModule } from '../../../test.module';
import { BankcardAuthRecordComponent } from 'app/entities/bankcard-auth-record/bankcard-auth-record.component';
import { BankcardAuthRecordService } from 'app/entities/bankcard-auth-record/bankcard-auth-record.service';
import { BankcardAuthRecord } from 'app/shared/model/bankcard-auth-record.model';

describe('Component Tests', () => {
    describe('BankcardAuthRecord Management Component', () => {
        let comp: BankcardAuthRecordComponent;
        let fixture: ComponentFixture<BankcardAuthRecordComponent>;
        let service: BankcardAuthRecordService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [BankcardAuthRecordComponent],
                providers: []
            })
                .overrideTemplate(BankcardAuthRecordComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BankcardAuthRecordComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BankcardAuthRecordService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new BankcardAuthRecord(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.bankcardAuthRecords[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
