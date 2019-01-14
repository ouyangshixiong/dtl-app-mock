/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { StoreAuthRecordUpdateComponent } from 'app/entities/store-auth-record/store-auth-record-update.component';
import { StoreAuthRecordService } from 'app/entities/store-auth-record/store-auth-record.service';
import { StoreAuthRecord } from 'app/shared/model/store-auth-record.model';

describe('Component Tests', () => {
    describe('StoreAuthRecord Management Update Component', () => {
        let comp: StoreAuthRecordUpdateComponent;
        let fixture: ComponentFixture<StoreAuthRecordUpdateComponent>;
        let service: StoreAuthRecordService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [StoreAuthRecordUpdateComponent]
            })
                .overrideTemplate(StoreAuthRecordUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(StoreAuthRecordUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StoreAuthRecordService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new StoreAuthRecord(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.storeAuthRecord = entity;
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
                    const entity = new StoreAuthRecord();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.storeAuthRecord = entity;
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
