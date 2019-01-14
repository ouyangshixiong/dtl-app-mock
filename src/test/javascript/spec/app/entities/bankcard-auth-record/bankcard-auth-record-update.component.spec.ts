/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { BankcardAuthRecordUpdateComponent } from 'app/entities/bankcard-auth-record/bankcard-auth-record-update.component';
import { BankcardAuthRecordService } from 'app/entities/bankcard-auth-record/bankcard-auth-record.service';
import { BankcardAuthRecord } from 'app/shared/model/bankcard-auth-record.model';

describe('Component Tests', () => {
    describe('BankcardAuthRecord Management Update Component', () => {
        let comp: BankcardAuthRecordUpdateComponent;
        let fixture: ComponentFixture<BankcardAuthRecordUpdateComponent>;
        let service: BankcardAuthRecordService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [BankcardAuthRecordUpdateComponent]
            })
                .overrideTemplate(BankcardAuthRecordUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BankcardAuthRecordUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BankcardAuthRecordService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new BankcardAuthRecord(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.bankcardAuthRecord = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new BankcardAuthRecord();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.bankcardAuthRecord = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
